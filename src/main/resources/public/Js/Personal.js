$($.ajax({
        type: 'GET',
        url: '/forecast',
        error: function () {
            $('#forecast').html('ERROR');
        },
        success: function (data, textStatus, xhr) {
            if (xhr.status === 200) {
                $('#response').text(JSON.stringify(data, null, 2));
                $("#forecast").text("Received");

                for (city in data) {
                    console.log(data[city].city);
                    $("#cityData").append($('<option>', {
                        value: data[city].id,
                        text: data[city].city
                    }));
                }
            } else {
                $('#forecast').text('Nothing Here');
            }
        }

    })
);

$(document).ready(function () {
    $("#btnSearch").click(function () {
        let city = encodeURIComponent($('#city').text());
        let country = encodeURIComponent($('#country').text());
        if (city != "" || country != "") {
            $.ajax({
                type: 'GET',
                url: '/4daysforecast?city=' + city + '&country=' + country,
                error: function () {
                    $('#forecast').html('ERROR');
                },
                success: function (data) {
                    if (!data.response.hasOwnProperty('error') && !data.response.hasOwnProperty('results')) {
                        $("#forecast-data").text(JSON.stringify(data));
                        $("#availability").text("available");
                        $("#btnSend").removeClass("disabled");
                    } else {
                        $("#availability").text('Not available');
                        $("#btnSend").addClass("disabled");
                    }
                }
            });
        } else {
            $("#forecast").text("empty field");
        }
    });
    $("#btnSend").click(function () {
        if ($("#forecast-data").text() != "" && $("#forecast-data").text() != "ERROR") {
            if ($("#forecast-data").text().indexOf("results") == -1) {
                let data = JSON.parse($('#forecast-data').text());
                data.target = 'something';

                $.ajax({
                    type: 'POST',
                    url: '/forecast',
                    data: JSON.stringify(data),
                    contentType: 'application/json',
                    statusCode: {
                        422: function () {
                            alert("Ya agrego esta ciudad");
                        },
                        201: function () {
                            alert("guadado!");
                        }
                    },
                    success: function (data) {
                        $("#btnSend").addClass("disabled");
                        $("#forecast").text("guardado");
                        $("#forecast-data").text("");
                        $('#response').text("");
                        $("#buscador").val("");
                        $("#btnSearch").addClass("disabled");
                        getForecast();
                    },
                    error: function () {
                        $("#btnSend").addClass("disabled");
                        $('#forecast').text('ERROR');
                    }
                });
            } else {
                $("#forecast").text("ERROR");
            }
        } else {
            $("#forecast").text("ERROR");
        }
    });
    $("#btnGet").click(function () {
        getForecast();
    });

    function getForecast() {
        $.ajax({
            type: 'GET',
            url: '/forecast',
            error: function () {
                $('#forecast').html('ERROR');
            },
            success: function (data, textStatus, xhr) {
                if (xhr.status === 200) {
                    $('#response').text(JSON.stringify(data, null, 2));
                    $("#forecast").text("Received");

                    for (city in data) {
                        $("#cityData").empty();
                        $("#cityData").append($('<option>', {
                            value: data[city].id,
                            text: data[city].city
                        }));
                    }
                } else {
                    $('#forecast').text('Nothing Here');
                }
            }

        });

    }

    $("#btnDel").click(function () {
        $.ajax({
            type: 'DELETE',
            url: '/forecast',
            error: function () {
                $('#forecast').html('ERROR');
            },
            success: function (data, textStatus, xhr) {
                if (xhr.status === 200) {
                    $("#cityData").empty();
                    $('#response').text("");
                    $("#forecast").text("Delete");
                } else {
                    $('#forecast').text('');
                }
            }

        });

    });

    $("#btnFilter").click(function () {
        if ($("#response").text() != "" && $("#forecast-data").text() != "ERROR") {
            var id = $("#cityData").val();
            var dataArray = [];
            let tempData = {
                id: id,
                type: "temp",
                minTempC: $("#minTemp").val(),
                maxTempC: $("#maxTemp").val()
            };
            let humData = {
                    id: id,
                    type: "hum",
                    rain: $("#rain").prop('checked'),
                    minHum: $("#minHum").val(),
                    maxHum: $("#maxHum").val()
                };
            let windData = {
                    id: id,
                    type: "wind",
                    windDir: $("#windDir").val(),
                    minWind: $("#minWind").val(),
                    maxWind: $("#maxWind").val()
                };

            if ($("#tempCheck").prop('checked')) {
                dataArray.push(tempData);
            }
            if ($("#rainCheck").prop('checked')) {
                dataArray.push(humData);
            }
            if ($("#windCheck").prop('checked')) {
                dataArray.push(windData);
            }

            for (data in dataArray) {
                $.ajax({
                    type: 'POST',
                    url: '/filters',
                    data: JSON.stringify(dataArray[data]),
                    contentType: 'application/json',
                    success: function (data) {
                        $("#forecast").text("Sended Filter");
                        $("#forecast-data").text("");
                        $('#response').text("");
                    },
                    error: function () {
                        $('#forecast').text('ERROR');
                    }
                });
            }
        } else {
            $("#forecast").text("ERROR");
        }

    });


    var addressPicker = new AddressPicker();

    $('#buscador').typeahead(null, {
        displayKey: 'description',
        source: addressPicker.ttAdapter()
    });
    addressPicker.bindDefaultTypeaheadEvent($('#buscador'))
    $(addressPicker).on('addresspicker:selected', function (event, result) {
        displayResults(result, $('#response'))
    })
    $(addressPicker).on('addresspicker:predictions', function (event, result) {
        if (result && result.length > 0)
            $('#buscador').removeClass("empty")
        else
            $('#buscador').addClass("empty")
    })

    function displayResults(result, div) {
        result.addressTypes().forEach(function (type) {
            if (type == "country") {
                $("#country").text(result.nameForType(type).replace(/ /g, "_"));
            }
            if (type == "political") {
                $("#city").text(result.nameForType(type).replace(/ /g, "_"));
            }

        })
        $("#btnSearch").removeClass("disabled");

        html = ["Address: " + result.address()]
        html.push("Latitude: " + result.lat())
        html.push("Longitude: " + result.lng())
        html.push("Long names:")
        result.addressTypes().forEach(function (type) {
            html.push("  " + type + ": " + result.nameForType(type))
        })
        html.push("Short names:")
        result.addressTypes().forEach(function (type) {
            html.push("  " + type + ": " + result.nameForType(type, true))
        })
        div.html(html.join('\n'));
    }

    var action;
    $(".number-spinner button").mousedown(function () {
        btn = $(this);
        input = btn.closest('.number-spinner').find('input');
        btn.closest('.number-spinner').find('button').prop("disabled", false);

        if (btn.attr('data-dir') == 'up') {
            action = setInterval(function () {
                if (input.attr('max') == undefined || parseInt(input.val()) < parseInt(input.attr('max'))) {
                    input.val(parseInt(input.val()) + 1);
                } else {
                    btn.prop("disabled", true);
                    clearInterval(action);
                }
            }, 50);
        } else {
            action = setInterval(function () {
                if (input.attr('min') == undefined || parseInt(input.val()) > parseInt(input.attr('min'))) {
                    input.val(parseInt(input.val()) - 1);
                } else {
                    btn.prop("disabled", true);
                    clearInterval(action);
                }
            }, 50);
        }
    }).mouseup(function () {
        clearInterval(action);
    });
});