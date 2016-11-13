var email;
var password;

$(document).ready(function () {
    $('#email').blur(function(){
        email = $('#email').val();
         console.log("emal: " + email + " password: " + password);
    });
    $('#password').blur(function(){
        password = $('#password').val();
         console.log("emal: " + email + " password: " + password);
    });
    $("#submit").click(function () {
        getUser(email, password);
    });
});
function getUser(email, password) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetUser?email=" + email + "&password=" + password,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var userID = data.userID;
            var fullname = data.fullname;
            var posID = data.posID;
            var position = data.position;
            
            sessionStorage.setItem("userID", userID);
            var storeUserID = sessionStorage.getItem("userID");
            sessionStorage.setItem("fullname", fullname);
            var storefullname = sessionStorage.getItem("fullname");
            sessionStorage.setItem("posID", posID);
            var storePosID = sessionStorage.getItem("posID");
            sessionStorage.setItem("position", position);
            var storePosition = sessionStorage.getItem("position");
        },
        error: function (response) {
            console.log(response);
        }
    });
}