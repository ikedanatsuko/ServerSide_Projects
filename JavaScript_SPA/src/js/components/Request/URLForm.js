import React from "react";

export default class URLForm extends React.Component {

  handleChange(e) {
    const url = e.target.value;
    this.props.setURL(url);
  }

  render() {
    return (
      <ul>
        <li>
          <p>URL</p>
        </li>
        <li>
          <p>http://localhost:2222/api/items
            <input type="text" size="60" onChange={this.handleChange.bind(this)} />
          </p>
        </li>
      </ul>
    );
  }
}
