import React from "react";

export default class HttpMethodForm extends React.Component {

  handleChange(e) {
    const select = e.target.value;
    this.props.setMethod(select);
  }

  render() {
    const methods = [
      'GET',
      'POST',
      'PUT',
      'DELETE'
    ];

    const methodOptions = [];
    for (const i in methods) {
      const method = methods[i];
      methodOptions.push(
        <option value={method}>{method}</option>
      );
    }

    return (
      <ul>
        <li>
          <label>Method</label>
          <select onChange={this.handleChange.bind(this)}>
            {methodOptions}
          </select>
        </li>
      </ul>
    );
  }
}
