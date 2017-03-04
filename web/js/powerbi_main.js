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
        divDashboard.append('<iframe src="https://app.powerbi.com/view?r=eyJrIjoiOGRlZDg5YzMtNzI5Ny00ZTI4LWE2ZDEtMzk5OGE0ODQ0MWI4IiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    } else if (posID == 2) {
        divDashboard.append('<iframe src="https://app.powerbi.com/view?r=eyJrIjoiNzA1YjFiNmUtZTYyOC00MjNkLWJkMjctMmRiOTc1ZGU2NGFlIiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    } else {
        divDashboard.append('<iframe src="https://app.powerbi.com/view?r=eyJrIjoiODFhYTg3YzYtZjI5Zi00YWJhLWJiNzAtNWI2ZjZhNDg1MDczIiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    }
}