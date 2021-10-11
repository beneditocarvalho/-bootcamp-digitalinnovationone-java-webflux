import React, { Component } from 'react';

class LineChart extends Component{
    constructor(){
        super();
        this.updateChart = this.updateChart.bind(this);
    }

    componentDidMount(){
        setInterval(this.updateChart, 1000);
    }

    updateChart(){
        console.log('teste');
    }

    render() {
        return (
        <div>Gráfico</div>
        );
	}

}

export default LineChart;