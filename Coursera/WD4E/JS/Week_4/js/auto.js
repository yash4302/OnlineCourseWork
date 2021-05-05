// JavaScript source code
function billingFunction() {
	var checkBox = document.getElementById("same");
	var billingName = document.getElementById("billingName");
	var billingZip = document.getElementById("billingZip");

	if (checkBox.checked) {
		billingName.value = document.getElementById("shippingName").value;
		billingZip.value = document.getElementById("shippingZip").value;
	}
	else {
		billingName.value = null;
		billingZip.value = null;
	}
}