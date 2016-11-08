var logout = $("#logout");

$(document).ready(function () {
    logout.click(function(){
        logout();
    });
});

function logout(){
    $.ajax({
        type: "POST",
        url: "/OBESystem/Logout",
        dataType: 'json',
        success: function (data) {
            console.log(data);
        },
        error: function (response) {
            console.log(response);
        }
    });
}