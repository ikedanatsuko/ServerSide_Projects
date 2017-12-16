import React from "react";

import request from "superagent";
import style from "../../../css/style.css";
import config from "../../../../config/config";

export default class Send extends React.Component {

  handleClick(e) {
    const tokenHeaderName = config.tokenHeaderName;
    const defaultURL = config.defaultURL;
    const url = this.props.url;

    const title = this.props.title;
    const note = this.props.note;
    const price = this.props.price;
    const image = this.props.image;

    const setResponse = this.props.setResponse.bind(this);

    function end(res) {
      const status = res.statusCode;
      const header = JSON.stringify(res.headers);
      const body = JSON.stringify(res.body);
      setResponse(status, header, body);
    }

    switch (this.props.method) {
      case "GET":
        request
          .get(defaultURL + url)
          .set(tokenHeaderName, this.props.token)
          .end(function(err, res) {
            end(res);
          }.bind(this));
        break;
      case "POST":
        request
          .post(defaultURL + url)
          .set("token", this.props.token)
          .send({title: title, note: note, price: price, image: image})
          .end(function(err, res) {
            end(res);
          }.bind(this));
        break;
        case "PUT":
          request
            .put(defaultURL + url)
            .set("token", this.props.token)
            .send({title: title, note: note, price: price, image: image})
            .end(function(err, res) {
              end(res);
            }.bind(this));
          break;
          case "DELETE":
            request
              .del(defaultURL + url)
              .set("token", this.props.token)
              .end(function(err, res) {
                end(res);
              }.bind(this));
            break;
      default:
    }
  }

  render() {
    return (
      <div className={style.send}>
        <input type="button" value="send" onClick={this.handleClick.bind(this)}/>
      </div>
    );
  }
}
