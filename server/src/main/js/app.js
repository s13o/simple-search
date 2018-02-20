'use strict';

const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {docs: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/all'}).done(response => {
            this.setState({docs: response.entity});
        });
    }

    render() {
        return (
            <DocList docs={this.state.docs}/>
        )
    }
}

class DocList extends React.Component{
    render() {
        var docs = this.props.docs.map(doc =>
            <Doc key={doc.key} doc={doc}/>
    );
        return (
            <table>
                <tbody>
                <tr>
                    <th>Key</th>
                    <th>Content</th>
                </tr>
                {docs}
                </tbody>
            </table>
        )
    }
}

class Doc extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.doc.key}</td>
                <td>{this.props.doc.content}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById('react')
)

