var example1 = $("#example1").DataTable({"order": []});
var examaple2 = $('#example2').DataTable({
    "paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false,
    "order": []
});
var rowCount = 0;
var arrSyllabusID = [];
var arrCurriculumID = [];
var arrCourseID = [];
var arrTerm = [];
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    if (posID == 7 || posID == 1) {
    } else {
        $('#button-new').hide();
    }
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetCurrentSyllabus",
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

    $("#button-past").click(function () {
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetPastSyllabus",
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
});

function addRow(data) {
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var term = data.term;
    var curriculumTitle = data.curriculumTitle;
    var syllabusID = data.syllabusID;
    var AY = data.startYear + " - " + data.endYear;
    var tools;

    if (posID == 7 || posID == 1) {
        tools = "<a href=\"/OBESystem/RedirectToViewSyllabus\"><button onclick=\"save('" + rowCount + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    } else {
        tools = "<a href=\"/OBESystem/RedirectToViewSyllabus\"><button onclick=\"save('" + rowCount + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    }

    arrSyllabusID.push(syllabusID);

    console.log("rowCount: " + rowCount);
    console.log("syllabusID pushed: " + syllabusID);
    example1.row.add([codeCourse, courseTitle, AY, term, curriculumTitle, tools]);
    example1.draw();
    rowCount++;
}

function save(count) {
    sessionStorage.setItem("syllabusID", arrSyllabusID[count]);
    var syllabusID = sessionStorage.getItem("syllabusID");
    console.log("syllabusID: " + syllabusID);
}