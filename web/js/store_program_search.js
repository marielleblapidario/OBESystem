var title = sessionStorage.getItem("programTitle");
var programCode = sessionStorage.getItem("programCode");
var collegeTitle = sessionStorage.getItem("college");
var collegeID = sessionStorage.getItem("collegeID");

$('#program-title').text(title);
$('#college-title').text(collegeTitle);
$('#hidden-program-title').val(programCode);
$('#hidden-college-title').val(collegeID);
