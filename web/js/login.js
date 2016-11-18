var posID = $('#posID').val();
var userID = $('#userID').val();

sessionStorage.setItem("posID", posID);
var s = sessionStorage.getItem("posID");
sessionStorage.setItem("userID", userID);
var ss = sessionStorage.getItem("userID");
console.log("posID: ", s);
console.log("userID: ", ss);