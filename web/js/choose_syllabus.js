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
var arrSyllabusID = [];

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllSyllabus",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
             $(".main-sidebar").trigger("create");
        },
        error: function (response) {
            console.log(response);
        }
    });
});

function addRow(data) {
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var term = data.term;
    var curriculumTitle = data.curriculumTitle;
    var syllabusID = data.syllabusID;
    var AY = data.startYear + " - " + data.endYear;

    var tools = "<a href=\"/OBESystem/RedirectToCreateOffering\"><button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn btn-success btn-sm\">select</button></a>";
    arrSyllabusID.push(syllabusID);

    console.log("rowCount: " + rowCount);
    console.log("syllabusID pushed: " + syllabusID);
    example1.row.add([codeCourse, courseTitle, term, AY, curriculumTitle, tools]);
    example1.draw();
    rowCount++;
}

function save(count) {
    sessionStorage.setItem("syllabusID", arrSyllabusID[count]);
    var syllabusID = sessionStorage.getItem("syllabusID");
    console.log("syllabusID: " + syllabusID);
}