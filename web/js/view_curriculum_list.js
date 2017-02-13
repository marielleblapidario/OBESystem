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
var posID = sessionStorage.getItem("posID");
var userID = sessionStorage.getItem("userID");
console.log("posID: ", posID);
console.log("userID: ", userID);

$(document).ready(function () {
    if (posID == 6 || posID == 1) {
    } else {
        $('#button-new').hide();
    }
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllCurriculum",
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
}
);

function addRow(data) {
    var codeCurriculum = data.codeCurriculum;
    var title = data.title;
    var programName = data.programName;
    var collegeName = data.collegeName;
    var startYear = data.startYear;
    var endYear = data.endYear;
    var contributor = data.contributorName;
    var tools;

    console.log("rowCount: " + rowCount);
    if (posID == 6 || posID == 1) {
        tools = "<a href=\"/OBESystem/RedirectToEditCurriculum\"><button onclick=\"save('" + codeCurriculum + "')\" title=\"edit\" type=\"button\" class=\"btn btn-success btn-xs\"><i class=\"fa fa-edit\"></i></button></a>" +
                "<a href=\"/OBESystem/RedirectToViewCurriculum\"><button  onclick=\"save('" + codeCurriculum + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>" +
                "<a href=\"/OBESystem/RedirectToMapCurriculum\"><button onclick=\"save('" + codeCurriculum + "')\" title=\"map\" type=\"button\" class=\"btn bg-orange btn-xs\"><i class=\"fa  fa-map-o\"></i></button></a>";

    } else {
        tools = "<a href=\"/OBESystem/RedirectToViewCurriculum\"><button  onclick=\"save('" + codeCurriculum + "')\" title=\"view\" type=\"button\" class=\"btn bg-purple btn-xs\"><i class=\"fa  fa-eye\"></i></button></a>";
    }

    example1.row.add([codeCurriculum, title, programName, collegeName, startYear, endYear, contributor, tools]);
    example1.draw();
    rowCount++;
}

function save(codeCurriculum) {
    sessionStorage.setItem("codeCurriculum", codeCurriculum);
    var ss = sessionStorage.getItem("codeCurriculum");
    console.log("codeCurriculum: " + ss);
}
