
var chartGraus = null;
var loopGraus = null;
function iniciarGraus()  { 	

	chartGraus = new Highcharts.Chart({  
        chart: {
            renderTo: 'graus',
            type: 'gauge',
            plotBackgroundColor: null,
            plotBackgroundImage: null,
            plotBorderWidth: 0,
            plotShadow: false,
            backgroundColor:'transparent'
        },
        credits: {
            enabled: false
        },
        title: {
            text: ' '
        },

        pane: {
            startAngle: -150,
            endAngle: 150,
            background: [{
                backgroundColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                    stops: [
                        [0, '#FFF'],
                        [1, '#333']
                    ]
                },
                borderWidth: 0,
                outerRadius: '109%'
            }, {
                backgroundColor: {
                    linearGradient: { x1: 0, y1: 0, x2: 0, y2: 1 },
                    stops: [
                        [0, '#333'],
                        [1, '#FFF']
                    ]
                },
                borderWidth: 1,
                outerRadius: '107%'
            }, {
                // default background
            }, {
                backgroundColor: '#DDD',
                borderWidth: 0,
                outerRadius: '105%',
                innerRadius: '103%'
            }]
        },

        // the value axis
        yAxis: {
            min: 0,
            max: 100,

            minorTickInterval: 'auto',
            minorTickWidth: 1,
            minorTickLength: 10,
            minorTickPosition: 'inside',
            minorTickColor: '#666',

            tickPixelInterval: 30,
            tickWidth: 2,
            tickPosition: 'inside',
            tickLength: 10,
            tickColor: '#666',
            labels: {
                step: 2,
                rotation: 'auto'
            },
            title: {
                text: ' Grau '
            },
            plotBands: [{
                from: 0,
                to: 40,
                color: '#DF5353' // green
            }, {
                from: 40,
                to: 60,
                color: '#DDDF0D' // yellow
            }, {
                from: 60,
                to: 100,
                color: '#55BF3B' // red
            }]
        },

        series: [{
            name: 'Grau',
            data: [0],
            tooltip: {
                valueSuffix: '  '
            }
        }],
        exporting: {
            buttons: {
            	contextButton: {
                    align: 'left',
                    x: 40,

                	menuItems: [{
                        textKey: 'downloadPNG',
                        onclick: function () {
                            this.exportChart();
                        }
                    }, {
                        textKey: 'downloadJPEG',
                        onclick: function () {
                            this.exportChart({
                                type: 'image/jpeg'
                            });
                        }
                    }]
                }	
            }
        }

    });
}


function requestDataGrau() {
 
    var point = chartGraus.series[0].points[0];
                                          
     var ponto = getRandomInt(0, 100); 
     point.update(ponto);

     loopGraus = setTimeout(requestDataGrau, 1000);   
}
function pararLoopGrau(){
	clearTimeout(loopGraus);
}