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

var selectedPO = [];
var selectedPOCodes = [];
var mapPOtoPA = [];
var checkboxDIV = $("#checkbox-div");

$(document).ready(function() {

    /*
     * hides the "Map PO to PA" button
     */
    $("#map-btn").hide();

    /*
     * when a row from the table is selected
     */
    $('#map-table tbody').on('click', 'tr', function() {
        selectedPO = [];
        selectedPOCodes = [];

        /*
         * loops all selected rows
         */
        table.rows('.selected').every(function(rowIdx) {

            /*
             * gets the code from the first column of the selected row
             */
            codePO = table.row(rowIdx).data()[0];

            /*
             * saves it to the arraylist
             */
            selectedPOCodes.push(codePO);

            /*
             * 'selectPA' is an object that contains the code and rowCount of 
             * the selected row
             */
            var selectPO = {code: codePO, ID: rowIdx};

            /*
             * saves it to arraylist
             */
            selectedPO.push(selectPO);
        });

        /*
         * checks if the map button should be shown or not
         */
        if (selectedPO.length > 0) {
            $("#map-btn").show();
        } else {
            $("#map-btn").hide();
        }
    });

    /*
     * when the "Map PO to PA" is selected, the selected POs will be shown in 
     * the modal
     */
    $("#map-btn").click(function() {
        $("#selected-POs").text(selectedPOCodes);
    });

    /*
     * for loop for to create the checkboxes for sample data of IGA
     * can be deleted afterwards
     * for checking only :)
     */
    for (var i = 0; i < 4; i++) {
        checkboxDIV.append("<input type='checkbox' value='PA" + i +
                "' class='checkbox-option'> PA" + i + "<br>");
    }

    /*
     * when the "CONFIRM" button in the modal is clicked
     */
    $('#modal-confirm-btn').click(function() {
        var selectedPA = [];
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
                selectedPA.push(elements[i].value);
            }
        }

        /*
         * loops through all the selected POs
         */
        for (var x = 0; selectedPO[x]; x++) {

            /*
             * loops through all the selected PAs
             */
            for (var j = 0; selectedPA[j]; j++) {

                /*
                 * 'map' is an object that contains the PO Code and its 
                 * assigned PA
                 */
                var map = {PO: selectedPO[x].code, PA: selectedPA[j]};

                /*
                 * object 'map' will be saved to the arraylist
                 */
                mapPOtoPA.push(map);

                /*
                 * selectedPA[x].ID = the rowCount
                 * 1 = the column number
                 * 
                 * updates the value of the row under the first column from 
                 * "No assigned PA" to the newly assigned PA(s)
                 */
                table.cell(selectedPO[x].ID, 1).data(selectedPA);
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
         * loops through all combinations of PA and IGA
         */
        for (var i = 0; mapPOtoPA[i]; i++) {
            var PO = mapPOtoPA[i].PO;
            var PA = mapPOtoPA[i].PA;

            /*
             * PO = PO code
             * PA = PA code
             * 
             * sends PO and PA as parameters
             * change URL to respective servlet URL
             */
            $.ajax({
                type: "POST",
                url: "/OBESystem/SampleMapPOtoPA?SelectedPO=" + PO +
                        "&SelectedPA=" + PA,
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
            data.forEach(addRow);
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