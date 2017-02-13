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
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    if (posID == 6 || posID == 1) {
    } else {
        $('#button-new-program').hide();
    }
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
    var tools;

    console.log("rowCount: " + rowCount);

    if (posID == 6 || posID == 1) {
        tools = "<a href=\"/OBESystem/RedirectToEditProgram\"><button onclick=\"save('" + codeProgram + "')\" title=\"edit\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button></a>\n\
 <a href=\"/OBESystem/RedirectToViewProgram\"><button onclick=\"save('" + codeProgram + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    } else {
        tools = "<a href=\"/OBESystem/RedirectToViewProgram\"><button  onclick=\"save('" + codeProgram + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    }

    example1.row.add([codeProgram, title, collegeName, contributorName, tools]);
    example1.draw();
    rowCount++;
}

function save(codeProgram) {
    sessionStorage.setItem("codeProgram", codeProgram);
    var ss = sessionStorage.getItem("codeProgram");
    console.log("codeProgram: " + codeProgram);
}
