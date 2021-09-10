<head>
	<title>Guessing Game for Yash Kavaiya</title>
</head>

<body>
	<h1>Welcome to my Guessing game.</h1>
	<?php
        $size = count($_GET);
        $num = $_GET['guess'];
        if($size == 0)
            echo "Missing guess parameter.";
        else {
            if($num == NULL)
                echo "Your guess is too short.";
            elseif ( !is_numeric($num) )
                echo "Your guess is Not a Number.";
            elseif ($num == 43)
                echo "Congratulations - You are right.";
            elseif ($num < 43)
                echo "Your guess is too low.";
            elseif ($num > 43)
                echo "Your guess is too high.";
        }
    ?>
</body>
