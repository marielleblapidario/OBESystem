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
        url: "/OBESystem/GetAllCurriculum",
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
    var codeCurriculum = data.codeCurriculum;
    var title = data.title;
    var programName = data.programName;
    var collegeName = data.collegeName;
    var startYear = data.startYear;
    var endYear = data.endYear;
    var contributor = data.contributorName;
    
    console.log("rowCount: " + rowCount);

    var tools = "<a href=\"/OBESystem/RedirectToEditCurriculum\"><button onclick=\"save('" + codeCurriculum + "')\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button></a>" +
            "<a href=\"/OBESystem/RedirectToViewCurriculum\"><button  onclick=\"save('" + codeCurriculum + "')\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>" +
            "<a href=\"/OBESystem/RedirectToMapCurriculum\"><button onclick=\"save('" + codeCurriculum + "')\" type=\"button\" class=\"btn bg-orange btn-xs\"><i class=\"fa  fa-map-o\"></i></button></a>" +
            "<button type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>";
    
    example1.row.add([codeCurriculum, title, programName, collegeName, startYear, endYear, contributor, tools]);
    example1.draw();
    rowCount++;
}

function save(codeCurriculum) {
    sessionStorage.setItem("codeCurriculum", codeCurriculum);
    var ss = sessionStorage.getItem("codeCurriculum");
    console.log("codeCurriculum: " + ss);
}
