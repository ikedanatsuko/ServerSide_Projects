import React from "react";
import HttpMethodForm from "./Request/HttpMethodForm";
import TokenForm from "./Request/TokenForm";
import URLForm from "./Request/URLForm";
import InputParams from "./Request/InputParams";
import Send from "./Request/Send";

export default class Request extends React.Component {

  render() {
    return (
      <div>
        <TokenForm setToken={this.props.setToken} />
        <HttpMethodForm setMethod={this.props.setMethod} />
        <URLForm setURL={this.props.setURL} />

        {/* Only when http request is POST or PUT */}
        <InputParams
          method={this.props.method} setInputParams={this.props.setInputParams} />

        <Send
          title={this.props.title} note={this.props.note} price={this.props.price} image={this.props.image}
          method={this.props.method} token={this.props.token} url={this.props.url} setResponse={this.props.setResponse} />
      </div>
    );
  }
}
