var divDashboard = $("#powerbi-include");
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    appendDashboard(posID);
    $("#button-print").click(function(){
       window.print();
    });
});

function appendDashboard(posID){
    if(posID == 4){
        divDashboard.append('<iframe width="100%" height="570" src="https://app.powerbi.com/view?r=eyJrIjoiYmY4NDY0ZjMtYWM5NC00NjNhLWEyOGUtZDFkZjZmMDMwODM2IiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    } else {
        divDashboard.append('<iframe width="100%" height="570" src="https://app.powerbi.com/view?r=eyJrIjoiOTc3MjIyMzktM2QwOC00OTRmLTljNDYtNmI3YTc3YTg5OTJiIiwidCI6ImYzNGEzNWJkLWE2NWQtNDYwNS1iMGZhLWQyNTcxZjgzMWY1ZSIsImMiOjEwfQ%3D%3D" frameborder="0" allowFullScreen="true"></iframe>');
    }
}
