var example1 = $("#example1").DataTable();
var examaple2 = $('#example2').DataTable({
    "paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false
});

$(document).ready(function () {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCourseList",
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
    var codeCourse = data.codeCourse;
    var courseID = data.courseID;
    var title = data.title;
    var contributorName = data.contributorName;
    
    console.log("courseID: " + courseID);

    var tools = "<button onclick=\"save('" + courseID + "')\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button>\n\
<a href=\"/OBESystem/RedirectToViewCourse\"><button onclick=\"save('" + courseID + "')\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>\n\
<a href=\"/OBESystem/RedirectToViewCourse\"><button onclick=\"save('" + courseID + "')\" type=\"button\" class=\"btn bg-orange btn-xs\"><i class=\"fa  fa-list-alt\"></i></button></a>\n\
<button onclick=\"save('" + courseID + "')\" type=\"button\" class=\"btn btn-danger btn-xs\"><i class=\"fa fa-trash\"></i></button>"
    example1.row.add([codeCourse, title, contributorName, tools]);
    example1.draw();
    
}

function save(courseID) {
    sessionStorage.setItem("courseID", courseID);
    var ss = sessionStorage.getItem("courseID");
    console.log("courseID: " + courseID);
}
