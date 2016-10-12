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
var arrCurriculumID = [];
var arrCourseID = [];
var arrTerm = [];

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllSyllabus",
        dataType: 'json',
        success: function (data) {
            console.log(data);
            data.forEach(addRow);
        },
        error: function (response) {
            console.log(response);
        }
    });
});

function addRow(data) {
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var programTitle = data.programTitle;
    var term = data.term;
    var curriculumTitle = data.curriculumTitle;
    var curriculumID = data.curriculumID;
    var courseID = data.courseID;
    var syllabusID = data.syllabusID;

    var tools = "<button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button>\n\
<a href=\"/OBESystem/RedirectToViewCourse\"><button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>\n\
<a href=\"/OBESystem/RedirectToOfferingsList\"><button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn bg-orange btn-xs\"><i class=\"fa  fa-list-alt\"></i></button></a>\n\
<button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>"
    arrCurriculumID.push(curriculumID);
    arrCourseID.push(courseID);
    arrTerm.push(term);
    arrSyllabusID.push(syllabusID);
    
    console.log("rowCount: " + rowCount);
    console.log("curriculumID pushed: " + curriculumID);
    console.log("courseID pushed: " + courseID);
    example1.row.add([codeCourse, courseTitle, programTitle, term, curriculumTitle, tools]);
    example1.draw();
    rowCount++;
}

function save(count) {
    sessionStorage.setItem("syllabusID", arrSyllabusID[count]);
    var syllabusID = sessionStorage.getItem("syllabusID");
    sessionStorage.setItem("curriculumID", arrCurriculumID[count]);
    var curriculumID = sessionStorage.getItem("curriculumID");
    sessionStorage.setItem("courseID", arrCourseID[count]);
    var courseID = sessionStorage.getItem("courseID");
    sessionStorage.setItem("term", arrTerm[count]);
    var term = sessionStorage.getItem("term");
    console.log("syllabusID: " + syllabusID);
    console.log("curriculumID: " + curriculumID);
    console.log("courseID: " + courseID);
    console.log("term: " + term);
}
