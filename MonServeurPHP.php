<?php
	
	ini_set('error_reporting', E_ALL ^ E_NOTICE);
	ini_set('display_errors', 1);
	// Set time limit to indefinite execution
	set_time_limit (0);
	ob_implicit_flush();

//---------------------------------------------------------------
	function InitServer(){
		echo "Hello World!<br/>";
		// Set the ip and port we will listen on
		$address = '127.0.0.1';
		$port = 21000;
		// Create a TCP Stream socket
		$sock = socket_create(AF_INET, SOCK_STREAM, 0);
		// Bind the socket to an address/port
		socket_bind($sock, $address, $port) or die('Could not bind to address');
		// Start listening for connections
		socket_listen($sock);
		// Non block socket type
		socket_set_nonblock($sock);	
		echo "Hello World 2!<br/>";
		return $sock;
	}

//--------------------------------------------------------------------------------------------
	function QuitServer($ListeClients=array(),$sock){
		
		 $j=0;
		 foreach ($ListeClients as $k)
       		 {
            		socket_write($k, "ByeBye client number $j>", 60).chr(0);
			$j++;
       		 }
		 sleep(5);
		 socket_close($sock);	 
}

//--------------------------------------------------------------------------------------------
	function ListenMessage($ListeClients=array(),$sock){
		while($buffer!='quit'){ 
		foreach ($ListeClients as $k)
       		 {
            		socket_recvfrom($ListeClients[0], $buf,256, $from, $port);
			$buf = trim($buf);
			socket_write($k, "You send".$buf." and you're".$from, 60).chr(0);
       		 }
		 }	
		 QuitServer($ListeClients,$sock);	
}


//-----------------------------------------------------------------
	function WaitForConnection($sock){
		
		$j=0;	
		$client=array();
		while(count($client)!=2){
			if($newsock = @socket_accept($sock)){
			    if(is_resource($newsock)){	
			    socket_write($newsock, "Welcome to my PHP server client number $j>", 60).chr(0);
			    echo "New client connected $j";
			    $client[$j] = $newsock;
			    $j++;
			    }			
			}				
		}
		ListenMessage($client,$sock);
	}



//-----------------------------------------------------------------	
	$sock = InitServer();
	WaitForConnection($sock);
	socket_close($sock);

?>
