import React from "react";

export default class Response extends React.Component {

  render() {
    return (
      <div>
        <ul>
          <li>
            <label>Status</label>
            <input readOnly type="text"
              value={this.props.status}/>
          </li>
          <li>
            <label>Header</label>
          <input size="48" readOnly type="text"
              value={this.props.header}/>
          </li>
          <li>
            <label>Body</label>
          <textarea rows="10" cols="46" readOnly value={this.props.body} />
          </li>
        </ul>
      </div>
    );
  }
}
