import React from "react";

export default class TokenForm extends React.Component {

  handleChange(e) {
    const token = e.target.value;
    this.props.setToken(token);
  }

  render() {
    return (
      <ul>
        <li>
          <label>Token</label>
        <input type="text" size="34" onChange={this.handleChange.bind(this)} />
        </li>
      </ul>
    );
  }
}
