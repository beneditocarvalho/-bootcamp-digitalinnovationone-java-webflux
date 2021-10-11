import React, { Component } from 'react';
import CanvasJSReact from './assets/canvas.react';
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

var openValues = [{
    x: 0.222,
    y: new Date()
},{
    x: 0.221,
    y: new Date()
}];

var closeValues = [{
    x: 0.122,
    y: new Date()
},{
    x: 0.121,
    y: new Date()
}];

class LineChart extends Component{
    constructor(){
        super();
        this.updateChart = this.updateChart.bind(this);
    }

    componentDidMount(){
        setInterval(this.updateChart, 1000);
    }

    updateChart(){
        this.chart.render();
    }

    render() {
        const options = {
            title:{
                text: 'Stock Quotes'
            },
            axisY: {
                includeZero: false,
                valueFormatString: '0.##0',
                title: 'Price in (USD)'
            },
            axisX: {
                valueFormatString: 'HH:mm',
            },
            data:[{
                type: 'line',
                name: 'Open Value',
                showInLegend: true,
                yValueFormatString: '0.##0',
                xValueFormatString: 'HH:mm',
                dataPoints: openValues
            },{
                type: 'line',
                name: 'Close Value',
                showInLegend: true,
                yValueFormatString: '0.##0',
                xValueFormatString: 'HH:mm',
                dataPoints: closeValues
            }]    
        };

        return (
        <div>
            <h1>Acompanhamento de cotação</h1>
            <CanvasJSChart options={options} onRef={ref => this.chart = ref}/> 
        </div>
        );
	}

}

export default LineChart;