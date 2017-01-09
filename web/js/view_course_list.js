var example1 = $("#example1").DataTable();
var examaple2 = $('#example2').DataTable({
    "paging": true,
    "lengthChange": false,
    "searching": false,
    "ordering": true,
    "info": true,
    "autoWidth": false
});
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
    var tools;

    console.log("courseID: " + courseID);
    if (posID == 7 || posID == 1) {
         tools = "<a href=\"/OBESystem/RedirectToEditCourse\"><button onclick=\"save('" + courseID + "')\" title=\"edit\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button></a>\n\
<a href=\"/OBESystem/RedirectToViewCourse\"><button onclick=\"save('" + courseID + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    } else {
         var tools = "<a href=\"/OBESystem/RedirectToViewCourse\"><button onclick=\"save('" + courseID + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    }
   example1.row.add([codeCourse, title, contributorName, tools]);
    example1.draw();

}

function save(courseID) {
    sessionStorage.setItem("courseID", courseID);
    var ss = sessionStorage.getItem("courseID");
    console.log("courseID: " + courseID);
}
