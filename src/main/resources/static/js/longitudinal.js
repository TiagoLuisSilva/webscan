
var chartLongitudinal = null;
var loopLongitudinal = null;
function iniciarLongitudinal()  { 	

	chartLongitudinal = new Highcharts.Chart({  
        chart: {
            renderTo: 'longitudinal',
            type: 'line',
            zoomType: 'x'
        },
        title: {
            text: ' '
        },
        subtitle: {
            text: ' '
        },
        credits: {
            enabled: false
        },
        legend: {
            enabled: false
        },
        xAxis: {
        	min: 0,
        	max: 1000
        },
        yAxis: {
        	 min: -100,

             max: 100,
            title: {
                text: 'Longitudinal'
            }
        }, 
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
        },
        series: [{
            name: 'Valor',
            data: [0],
            lineWidth: 1
        } ]
    });
}


function requestDataLongitudinal() {
 
    var series = chartLongitudinal.series[0];
    var shift = series.data.length > 1000;  
                                          
     var ponto = getRandomInt(-100, 100); 
     chartLongitudinal.series[0].addPoint(ponto, true, shift);
     
     loopLongitudinal = setTimeout(requestDataLongitudinal, 1000);   
}
function pararLoopLongitudinal(){
	clearTimeout(loopLongitudinal);
}