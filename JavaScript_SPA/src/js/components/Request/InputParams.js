import React from "react";

export default class InputParams extends React.Component {

  handleChange(e) {
    const title = document.getElementById("title").value;
    const note = document.getElementById("note").value;
    const price = document.getElementById("price").value;
    const image = document.getElementById("image").value;

    this.props.setInputParams(title, note, price, image);
  }

  render() {
    const method = this.props.method;
    if (method != "POST" &&  method != "PUT") {
      return null;
    }

    // Only when http request is POST or PUT
    return (
      <div>
        <form name="form" onChange={this.handleChange.bind(this)}>
          <ul>
            <li>
              <p>Input</p>
            </li>
            <li>
              <label>title</label>
              <input id="title" type="text" />
            </li>
            <li>
              <label>note</label>
              <input id="note" type="text" />
            </li>
            <li>
              <label>price</label>
              <input id="price" type="text" />
            </li>
            <li>
              <label>image</label>
              <input id="image" type="text" />
            </li>
          </ul>
        </form>
      </div>
    );
  }
}
