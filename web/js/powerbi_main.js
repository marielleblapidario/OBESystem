var divDashboard = $("#powerbi-include");
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    appendDashboard(posID);
    $("#button-print").click(function () {
        $("input").each(function() {
            $(this).removeAttr('placeholder');
        });
        $("textarea").each(function() {
            $(this).removeAttr('placeholder');
        });
        window.print();
    });
});

function appendDashboard(posID) {
    if (posID == 4) {
        divDashboard.append('<iframe src="https://app.powerbi.com/view?r=eyJrIjoiNWViYzZhOTgtOWU1ZS00ZGVhLThlNjctZDQ0ODU2ZmMwYWNkIiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    } else if (posID == 2) {
        divDashboard.append('<iframe src="https://app.powerbi.com/view?r=eyJrIjoiYjkyYTRjZGYtZjZmOS00OTJhLWIzOTgtZDc2MjZkZDlmMTI4IiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    } else {
        divDashboard.append('<iframe src="https://app.powerbi.com/view?r=eyJrIjoiZTU4OTA0YzQtY2NhMy00M2NjLTgzYjAtNzBhZmVhYjQ1MmRkIiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    }
}