var title = sessionStorage.getItem("programTitle");
var ss = sessionStorage.getItem("programCode");
var collegeTitle = sessionStorage.getItem("college");

$('#program-title').text(title);
$('#college-title').text(collegeTitle);
$('#hidden-program-title').val(title);
$('#hidden-college-title').val(collegeTitle);
