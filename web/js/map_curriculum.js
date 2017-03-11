var codeCurriculum = sessionStorage.getItem("codeCurriculum");
var tableDIV = $("#table-div");
var sizePI = 0;
var table;
var tbody;
var courseCodes = [];
var mapping = [];
var arrPI = [];
var arrCodePI = [];
var arrCourseID = [];

$(document).ready(function () {
    $('#PI-labels').hide();
    $('#btn-show').click(function () {
        if ($('#PI-labels').css('display') == 'none') {
            $("#btn-show").html('Hide PI');
            $('#PI-labels').show();
        } else {
            $("#btn-show").html('Show PI');
            $('#PI-labels').hide();
        }
    });
    function1().done(function () {
        function2().done(function () {
        });
    });
    //getMapping(codeCurriculum);
    $('#save-btn').click(function () {
        console.log("clicked");
        var elements = document.getElementsByClassName('checkbox');
        for (var i = 0; elements[i]; i++) {
            if (elements[i].checked) {
                var value = elements[i].value.split("_");
                var mapCurID = value[0];
                var courseID = value[1];
                var PI = value[2];
                var mapCombo = {mapCurID: mapCurID, courseID: courseID, codePI: PI, curriculumID: codeCurriculum};
                mapping.push(mapCombo);
                console.log(courseID + " is checked on " + PI + " curriculumID: " + codeCurriculum + "mapCurID: " + mapCurID);
            }
        }
        var jsonData = JSON.stringify(mapping);
        $.ajax({
            type: "POST",
            url: "/OBESystem/EncodeMapCurriculumToPI",
            dataType: 'json',
            data: {'jsonData': jsonData},
            success: function (data) {
                console.log(data);
            },
            error: function (response) {
                console.log(response);
            }
        });
    });
    $("#button-print").click(function () {
        window.print();
    });
});

function getMapping(codeCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetMapCurriculumToPI?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            for (var x = 0; x < data.length; x++) {
                arrCourseID.push(data[x].courseID);
                arrCodePI.push(data[x].codePI);
            }
            getSpecificCurriculum(codeCurriculum);
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getCourses(codeCurriculum) {
    console.log("codeCurriulum for courses: " + codeCurriculum);
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificMapCurriculumToCourse?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            var s = '<tbody id = "body">';
            table.append(s);
            data.forEach(appendTableRow);
            table.append('</tbody>');
            stickT();
        },
        error: function (response) {
            console.log(response);
        }
    });
}

function getSpecificCurriculum(codeCurriculum) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetSpecificCurriculum?SelectedCurriculum=" + codeCurriculum,
        dataType: 'json',
        success: function (data) {
            console.log(data);
            $('#title').text(data.title);
            var codeProgram = data.program;

            $.ajax({
                type: "GET",
                url: "/OBESystem/GetAllPIforCurriculum?program=" + codeProgram,
                dataType: 'json',
                success: function (data) {
                    console.log(data);
                    var s = "<table class='overflow-y' id='table' style='overflow-y:auto; width=100%; text-align:center;'>";
                    tableDIV.append(s);
                    table = $("#table");
                    var ss = "<thead><tr id='table-header'>";
                    table.append(ss);
                    var header = $("#table-header");
                    header.append("<th> Code </th>");
                    header.append("<th> Units  </th>");

                    var table2 = $("#tablePI");

                    for (var i = 0; i < data.length; i++) {
                        var a = "<th>" + data[i].codePI + " </th>";
                        header.append(a);
                        var piObj = {codePI: data[i].codePI, description: data[i].description};
                        arrPI.push(piObj);

                        //for instructions
                        var t = '<tr><td>' + data[i].codePI + '</td><td>' + data[i].description + '</td></tr>';
                        table2.append(t);
                    }
                    header.append("</tr></thead>");
                    getCourses(codeCurriculum);

                },
                error: function (response) {
                    console.log(response);
                }
            });

        },
        error: function (response) {
            console.log(response);
        }
    });
}
function appendTableRow(data) {
    var mapCurID = data.mapCurID;
    var courseID = data.courseID;
    var codeCourse = data.codeCourse;
    var units = data.units;
    courseCodes.push(codeCourse);
    var s = "<tr id=" + codeCourse + "> " +
            +"<th>" + codeCourse + "</th>"
            + "<th>" + codeCourse + "</th>"
            + "<td>" + units + "</td>";
    table.append(s);

    //start for printing
    var print = "<tr id=print" + codeCourse + "> " +
            "<th>" + codeCourse + "</th>" +
            "<td>" + units + "</td>" +
            "<td>" + data.yearLevel + "</td>" +
            "<td>" + data.term + "</td>";
    $('#table-print').append(print);
    var printRow = $("#print" + codeCourse);
    //append PIs in each course
    var printPI = "<td>";

    for (var x = 0; x < arrCodePI.length; x++) {
        if (courseID == arrCourseID[x]) {
            for (var i = 0; i < arrPI.length; i++) {
                if (arrPI[i].codePI == arrCodePI[x]) {
                    if (x == (arrCodePI.length - 1)) {
                        printPI += "<b>" + arrCodePI[x] + ":</b> " + arrPI[i].description;
                    } else {
                        printPI += "<b>" + arrCodePI[x] + ":</b> " + arrPI[i].description + " <br />";
                    }
                }
            }

        }
    }
    printPI += "</td></tr>";
    printRow.append(printPI);
    //end for printing

    var row = $("#" + codeCourse);
    for (var i = 0; i < arrPI.length; i++) {
        var codePI = mapCurID + "_" + courseID + "_" + arrPI[i].codePI;
        var checked = "";
        for (var x = 0; x < arrCodePI.length; x++) {
            if (courseID == arrCourseID[x]) {
                if (arrPI[i].codePI == arrCodePI[x]) {
                    checked = "checked";
                }
            }
        }
        var appendPI = "<td>"
                + "<label class=''>"
                + "<div style='position:relative;'>"
                + "<input value='" + codePI + "' type='checkbox' class='checkbox'" + checked + "></div>"
                + "</label>"
                + "</td>";
        row.append(appendPI);
    }
    row.append("</tr>");
}
function function1() {
    var dfrd1 = $.Deferred();
    setTimeout(function () {
        // doing async stuff
        getMapping(codeCurriculum);
        console.log('task 1 in function1 is done!');
        dfrd1.resolve();
    }, 1000);

    return dfrd1.promise();
}

function function2() {
    var dfrd1 = $.Deferred();
    setTimeout(function () {
        // doing async stuff

        console.log('task 1 in function2 is done!');
        dfrd1.resolve();
    }, 2000);
    return dfrd1.promise();
}
function stickT() {
    $('table').each(function () {
        if ($(this).find('thead').length > 0 && $(this).find('th').length > 0) {
            // Clone <thead>
            var $w = $(window),
                    $t = $(this),
                    $thead = $t.find('thead').clone(),
                    $col = $t.find('thead, tbody').clone();

            // Add class, remove margins, reset width and wrap table
            $t
                    .addClass('sticky-enabled')
                    .css({
                        margin: 0,
                        width: '100%'
                    }).wrap('<div class="sticky-wrap" />');

            if ($t.hasClass('overflow-y'))
                $t.removeClass('overflow-y').parent().addClass('overflow-y');

            // Create new sticky table head (basic)
            $t.after('<table class="sticky-thead" />');

            // If <tbody> contains <th>, then we create sticky column and intersect (advanced)
            if ($t.find('tbody th').length > 0) {
                $t.after('<table class="sticky-col" /><table class="sticky-intersect" />');
            }

            // Create shorthand for things
            var $stickyHead = $(this).siblings('.sticky-thead'),
                    $stickyCol = $(this).siblings('.sticky-col'),
                    $stickyInsct = $(this).siblings('.sticky-intersect'),
                    $stickyWrap = $(this).parent('.sticky-wrap');

            $stickyHead.append($thead);

            $stickyCol
                    .append($col)
                    .find('thead th:gt(0)').remove()
                    .end()
                    .find('tbody td').remove();

            $stickyInsct.html('<thead><tr><th>' + $t.find('thead th:first-child').html() + '</th></tr></thead>');

            // Set widths
            var setWidths = function () {
                $t
                        .find('thead th').each(function (i) {
                    $stickyHead.find('th').eq(i).width($(this).width());
                })
                        .end()
                        .find('tr').each(function (i) {
                    $stickyCol.find('tr').eq(i).height($(this).height());
                });

                // Set width of sticky table head
                $stickyHead.width($t.width());

                // Set width of sticky table col
                $stickyCol.find('th').add($stickyInsct.find('th')).width($t.find('thead th').width())
            },
                    repositionStickyHead = function () {
                        // Return value of calculated allowance
                        var allowance = calcAllowance();

                        // Check if wrapper parent is overflowing along the y-axis
                        if ($t.height() > $stickyWrap.height()) {
                            // If it is overflowing (advanced layout)
                            // Position sticky header based on wrapper scrollTop()
                            if ($stickyWrap.scrollTop() > 0) {
                                // When top of wrapping parent is out of view
                                $stickyHead.add($stickyInsct).css({
                                    opacity: 1,
                                    top: $stickyWrap.scrollTop()
                                });
                            } else {
                                // When top of wrapping parent is in view
                                $stickyHead.add($stickyInsct).css({
                                    opacity: 0,
                                    top: 0
                                });
                            }
                        } else {
                            // If it is not overflowing (basic layout)
                            // Position sticky header based on viewport scrollTop
                            if ($w.scrollTop() > $t.offset().top && $w.scrollTop() < $t.offset().top + $t.outerHeight() - allowance) {
                                // When top of viewport is in the table itself
                                $stickyHead.add($stickyInsct).css({
                                    opacity: 1,
                                    top: $w.scrollTop() - $t.offset().top
                                });
                            } else {
                                // When top of viewport is above or below table
                                $stickyHead.add($stickyInsct).css({
                                    opacity: 0,
                                    top: 0
                                });
                            }
                        }
                    },
                    repositionStickyCol = function () {
                        if ($stickyWrap.scrollLeft() > 0) {
                            // When left of wrapping parent is out of view
                            $stickyCol.add($stickyInsct).css({
                                opacity: 1,
                                left: $stickyWrap.scrollLeft()
                            });
                        } else {
                            // When left of wrapping parent is in view
                            $stickyCol
                                    .css({opacity: 0})
                                    .add($stickyInsct).css({left: 0});
                        }
                    },
                    calcAllowance = function () {
                        var a = 0;
                        // Calculate allowance
                        $t.find('tbody tr:lt(3)').each(function () {
                            a += $(this).height();
                        });

                        // Set fail safe limit (last three row might be too tall)
                        // Set arbitrary limit at 0.25 of viewport height, or you can use an arbitrary pixel value
                        if (a > $w.height() * 0.25) {
                            a = $w.height() * 0.25;
                        }

                        // Add the height of sticky header
                        a += $stickyHead.height();
                        return a;
                    };

            setWidths();

            $t.parent('.sticky-wrap').scroll($.throttle(250, function () {
                repositionStickyHead();
                repositionStickyCol();
            }));

            $w
                    .load(setWidths)
                    .resize($.debounce(250, function () {
                        setWidths();
                        repositionStickyHead();
                        repositionStickyCol();
                    }))
                    .scroll($.throttle(250, repositionStickyHead));
        }
    });
}