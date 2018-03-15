import React from "react";
import config from "../../../../config/config";

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
          <p>
            {config.defaultURL}
            <input type="text" size="60" onChange={this.handleChange.bind(this)} />
          </p>
        </li>
      </ul>
    );
  }
}
