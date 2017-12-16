import React from "react";
import Request from "./Request";
import Response from "./Response";
import style from "../../css/style.css";

export default class Layout extends React.Component {

  constructor() {
    super();
    this.state = {
      req_method: "GET",
      req_token: "",
      req_url: "",
      in_title: "",
      in_note: "",
      in_price: null,
      in_image: null,
      res_status: "",
      res_header: "",
      res_body: ""
    }
  }

  // request
  setMethod(method) {
    this.setState({req_method: method});
  }
  setToken(token) {
    this.setState({req_token: token});
  }
  setURL(url) {
    this.setState({req_url: url});
  }
  setInputParams(title, note, price, image) {
    this.setState({in_title: title});
    this.setState({in_note: note});
    this.setState({in_price: price});
    this.setState({in_image: image});
  }

  // response
  setResponse(status, header, body) {
    this.setState({res_status: status});
    this.setState({res_header: header});
    this.setState({res_body: body});
  }

  render() {
    return (
      <div>
        <h1>Request</h1>
        <Request
          method={this.state.req_method} token={this.state.req_token} url={this.state.req_url}
          title={this.state.in_title} note={this.state.note} price={this.state.price} image={this.state.image}
          setMethod={this.setMethod.bind(this)}
          setToken={this.setToken.bind(this)}
          setURL={this.setURL.bind(this)}
          setResponse={this.setResponse.bind(this)}
          setInputParams={this.setInputParams.bind(this)}/>

        <h1>Response</h1>
        <Response
          status={this.state.res_status}
          header={this.state.res_header}
          body={this.state.res_body}/>
      </div>
    );
  }
}
