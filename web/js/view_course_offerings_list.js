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
var arrOfferingID = [];
var arrSyllabusID = [];
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);


$(document).ready(function () {
    if (posID == 2) {
        $('#button-new').hide();
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetOfferingsOfFaculty?userID=" + userID,
            dataType: 'json',
            success: function (data) {
                console.log(data);
                data.forEach(addRow);
            },
            error: function (response) {
                console.log(response);
            }
        });
    } else {
        $.ajax({
            type: "GET",
            url: "/OBESystem/GetAllOfferings",
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
});

function addRow(data) {
    var offeringID = data.offeringID;
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var term = data.term;
    var section = data.section;
    var days = data.days;
    var time = data.time;
    var roomTitle = data.roomTitle;
    var facultyName = data.facultyName;
    var AY = data.startYear + " - " + data.endYear;
    var syllabusID = data.syllabusID;
    var tools;

    console.log("rowCount: " + rowCount);
    if (posID == 4 || posID == 1) {
        tools = "<button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button>\n\
<a href=\"/OBESystem/RedirectToViewCourseOffering\"><button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>\n\
<button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>";
    } else {
        tools = "<a href=\"/OBESystem/RedirectToViewCourseOffering\"><button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    }
    arrOfferingID.push(offeringID);
    arrSyllabusID.push(syllabusID);
    console.log("pushed: ", offeringID);

    example1.row.add([codeCourse, courseTitle, AY, term, section, days, time, roomTitle, facultyName, tools]);
    example1.draw();
    rowCount++;
}

function save(count) {
    sessionStorage.setItem("offeringID", arrOfferingID[count]);
    var offeringID = sessionStorage.getItem("offeringID");
    sessionStorage.setItem("syllabusID", arrSyllabusID[count]);
    var syllabusID = sessionStorage.getItem("syllabusID");
    console.log("syllabusID: " + syllabusID);
    console.log("offeringID: " + offeringID);
}