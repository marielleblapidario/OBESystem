var title = sessionStorage.getItem("title");
var codeCourse = sessionStorage.getItem("codeCourse");

$('#course-title').text(title);
$('#hidden-codeCourse').val(codeCourse);

console.log("course title: " + title);