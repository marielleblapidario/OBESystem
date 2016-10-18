var example1 = $("#example1").DataTable();
var examaple2 = $('#example2').DataTable({
    "paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false
}); 
rowCount = 0;
arrOfferingID = [];

$(document).ready(function () {
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
});

function addRow(data) {
    var offeringID = data.offeringID;
    var curriculumID = data.curriculumID;
    var courseID = data.courseID;
    var codeCourse = data.codeCourse;
    var courseTitle = data.courseTitle;
    var term = data.term;
    var section = data.section;
    var days = data.days;
    var time = data.time;
    var room = data.room;
    var roomTitle = data.roomTitle;
    var facultyName = data.facultyName;
    
    console.log("rowCount: " + rowCount);
    var tools = "<button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button>\n\
<a href=\"/OBESystem/RedirectToViewCourse\"><button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>\n\
<a href=\"/OBESystem/RedirectToOfferingsList\"><button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn bg-orange btn-xs\"><i class=\"fa  fa-list-alt\"></i></button></a>\n\
<button onclick=\"save('" + rowCount + "')\" type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>";
    
    example1.row.add([codeCourse, courseTitle, term, section, days, time, roomTitle, facultyName, tools]);
    example1.draw();
    rowCount++;
}