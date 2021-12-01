<head>
	<title>dbfa932e Yash Kavaiya</title>
</head>

<body>
	<h1>Welcome to my Guessing game.</h1>
	<?php
        $size = count($_GET);
        if($size == 0)
            echo "Missing guess parameter.";
        else {
            $num = $_GET['guess'];
            if($num == NULL)
                echo "Your guess is too short.";
            elseif ( !is_numeric($num) )
                echo "Your guess is Not a Number.";
            elseif ($num == 74)
                echo "Congratulations - You are right.";
            elseif ($num < 74)
                echo "Your guess is too low.";
            elseif ($num > 74)
                echo "Your guess is too high.";
        }
    ?>
</body>
