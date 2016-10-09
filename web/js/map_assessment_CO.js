var programCode = sessionStorage.getItem("programCode");
var table = $('#map-table').DataTable({
    "processing": false,
    "paging": false,
    "lengthChange": false,
    "ordering": false,
    "info": true,
    select: {
        style: 'multi'
    }
});

var selectedAssessment = [];
var selectedAssessmentCodes = [];
var mapAssessmenttoCO = [];
var checkboxDIV = $("#checkbox-div");

$(document).ready(function() {

    /*
     * hides the "Map Assessment to CO" button
     */
    $("#map-btn").hide();

    /*
     * when a row from the table is selected
     */
    $('#map-table tbody').on('click', 'tr', function() {
        selectedAssessment = [];
        selectedAssessmentCodes = [];

        /*
         * loops all selected rows
         */
        table.rows('.selected').every(function(rowIdx) {

            /*
             * gets the code from the first column of the selected row
             */
            codeAssessment = table.row(rowIdx).data()[0];

            /*
             * saves it to the arraylist
             */
            selectedAssessmentCodes.push(codeAssessment);

            /*
             * 'selectCO' is an object that contains the code and rowCount of 
             * the selected row
             */
            var selectAssessment = {code: codeAssessment, ID: rowIdx};

            /*
             * saves it to arraylist
             */
            selectedAssessment.push(selectAssessment);
        });

        /*
         * checks if the map button should be shown or not
         */
        if (selectedAssessment.length > 0) {
            $("#map-btn").show();
        } else {
            $("#map-btn").hide();
        }
    });

    /*
     * when the "Map Assessment to CO" is selected, the selected Assessents will be shown in 
     * the modal
     */
    $("#map-btn").click(function() {
        $("#selected-Assessments").text(selectedAssessmentCodes);
    });

    /*
     * for loop for to create the checkboxes for sample data of IGA
     * can be deleted afterwards
     * for checking only :)
     */
    for (var i = 0; i < 4; i++) {
        checkboxDIV.append("<input type='checkbox' value='CO" + i +
                "' class='checkbox-option'> CO" + i + "<br>");
    }

    /*
     * when the "CONFIRM" button in the modal is clicked
     */
    $('#modal-confirm-btn').click(function() {
        var selectedCO = [];
        /*
         * elements is an arraylist that contains all the checkboxes
         */
        var elements = document.getElementsByClassName('checkbox-option');
        for (var i = 0; elements[i]; i++) {

            /*
             * if the checkbox is checked, its value will be saved to the 
             * arraylist
             */
            if (elements[i].checked) {
                selectedCO.push(elements[i].value);
            }
        }

        /*
         * loops through all the selected Assessments
         */
        for (var x = 0; selectedAssessment[x]; x++) {

            /*
             * loops through all the selectedCOs
             */
            for (var j = 0; selectedCO[j]; j++) {

                /*
                 * 'map' is an object that contains the Assessment Code and its 
                 * assigned CO
                 */
                var map = {Assessment: selectedAssessment[x].code, CO: selectedCO[j]};

                /*
                 * object 'map' will be saved to the arraylist
                 */
                mapAssessmenttoCO.push(map);

                /*
                 * selecteCO[x].ID = the rowCount
                 * 1 = the column number
                 * 
                 * updates the value of the row under the first column from 
                 * "No assigned CO" to the newly assigne IGA(s)
                 */
                table.cell(selectedAssessment[x].ID, 1).data(selectedCO);
            }
        }

        /*
         * deselects all selected checkboxed from the modal        
         */
        $(".checkbox-option").prop('checked', false);
    });

    /*
     * when the 'Save' button is clicked
     */
    $("#save-btn").click(function() {

        /*
         * loops through all combinations of Assessments and CO
         */
        for (var i = 0; mapAssessmenttCO[i]; i++) {
            var Assessment = mapAssessmenttCO[i].Assessment;
            var CO = mapAssessmenttCO[i].CO;

            /*
             * Assessment = Assessment code
             * CO = CO code
             * 
             * sends Assessment and CO as parameters
             * change URL to respective servlet URL
             */
            $.ajax({
                type: "POST",
                url: "/OBESystem/SampleMapAssessmenttoCO?SelectedAssessment=" + Assessment +
                        "&SelectedCO=" + CO,
                dataType: 'json',
                success: function() {
                },
                error: function(response) {
                    console.log(response);
                }
            });
        }
    });
});

function getAllPA(program) {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllPA?SelectedProgram=" + program,
        dataType: 'json',
        success: function(data) {
            console.log(data);
//            data.forEach(addRow);
            var baseRow = $("#base-row");
            baseRow.remove();

            /*
             * clones the base row
             */
            var newBaseRow = baseRow.clone();

            newBaseRow.find("#code").text("Hello");
            getAllIGA();
        },
        error: function(response) {
            console.log(response);
        }
    });
}

function addRow(data) {
    var PA = data.codeProgram;
    var IGA = "No assigned IGA";
    var Status = "<span class='label label - success'>pending</span>";
    var Remarks = "<div class='col-sm-10'><input type='text' name='remarks' \n\
    class='form-control no-border' id='remarks'></div>";

    table.row.add(PA, IGA, Status, Remarks);
}

function getAllIGA() {
    $.ajax({
        type: "GET",
        url: "/OBESystem/GetAllIGA",
        dataType: 'json',
        success: function(data) {
            console.log(data);
            data.forEach(addOption);
        },
        error: function(response) {
            console.log(response);
        }
    });
}

function addOption(data) {
    checkboxDIV.append("<input type='checkbox' value='" + data.codeIGA + "' class='checkbox-option'> " + data.description + "<br>");
}