var example1 = $("#example1").DataTable();
var examaple2 = $('#example2').DataTable({
    "paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false
});
var rowCount = 0;

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/OBESystem/ViewProgramList",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
        },
        error: function (response) {
            console.log(response);
        }
    });
}
);

function addRow(data) {
    var codeProgram = data.codeProgram;
    var title = data.title;
    var collegeName = data.collegeName;
    console.log("college name: " + collegeName);
    var contributorName = data.contributorName;

    console.log("rowCount: " + rowCount);

    var tools = "<a href=\"/OBESystem/RedirectToEditProgram\"><button onclick=\"save('" + codeProgram + "')\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button></a>\n\
 <a href=\"/OBESystem/RedirectToViewProgram\"><button  onclick=\"save('" + codeProgram + "')\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>\n\
<button type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>"
    example1.row.add([codeProgram, title, collegeName, contributorName, tools]);
    example1.draw();
    rowCount++;
}

function save(codeProgram) {
    sessionStorage.setItem("codeProgram", codeProgram);
    var ss = sessionStorage.getItem("codeProgram");
    console.log("codeProgram: " + codeProgram);
}
