<?php
$error = false;
$md5 = false;
$code = "";
if ( isset($_GET['code']) ) {
    $code = $_GET['code'];
    $val = array('0','1','2','3','4','5','6','7','8','9');
    if ( strlen($code) != 4 ) {
        $error = "Input must be exactly four characters";
    }
    else if ( in_array($code[0], $val) && in_array($code[1], $val) && in_array($code[2], $val) && in_array($code[3], $val) ) {
        $md5 = hash('md5', $code);
    }
    else {
        $error = "Input must be integer";
    }
}
?>

<head>
	<title>PIN Code</title>
</head>

<body>
	<h1>MD5 PIN Maker</h1>
	
	<?php
        if ( $error !== false ) {
            print '<p style="color:red">';
            print htmlentities($error);
            print "</p>\n";
        }

        if ( $md5 !== false ) {
            print "<p>MD5 value: ".htmlentities($md5)."</p>";
        }
    ?>
	
	<p>Please enter a four-letter key for encoding.</p>

	<form>
		<input type="text" name="code" value="<?= htmlentities($code) ?>"/>
		<input type="submit" value="Compute MD5 for CODE"/>
	</form>
	
	<p><a href="makecode.php">Reset</a></p>
	<p><a href="index.php">Back to Cracking</a></p>
</body>
