import React from "react";
import Typography from "@material-ui/core/Typography";
import Icon from "@material-ui/core/Icon";
import Avatar from "@material-ui/core/Avatar";
import BlockIcon from "@material-ui/icons/Block";
import CloudUploadIcon from "@material-ui/icons/CloudUpload";
import withStyles from "@material-ui/core/styles/withStyles";
import customImageInputStyle from "./CustomImageInputStyle";
import classnames from "classnames";
import { Button } from "@material-ui/core";
import ConstantList from "../../../appConfig";

class ImageInput extends React.Component {
  constructor(props) {
    super(props);
    this.fileUpload = React.createRef();
    this.showFileUpload = this.showFileUpload.bind(this);
    this.handleImageChange = this.handleImageChange.bind(this);
  }

  state = {
    file: undefined,
    imagePreviewUrl: undefined,
  };

  getImageNameAndType = (name) => {
    // alert(name)
    if (name) {
      return name.split(".")[0] + "/" + name.split(".")[1];
    }
    return "";
  };

  showFileUpload() {
    if (this.fileUpload) {
      this.fileUpload.current.click();
    }
  }

  handleImageChange(e) {
    e.preventDefault();
    let reader = new FileReader();
    let file = e.target.files[0];
    if (file) {
      reader.onloadend = () => {
        this.setState({
          file: file,
          imagePreviewUrl: reader.result,
        });
      };
      reader.readAsDataURL(file);
      this.props.onChange(this.props.field.name, file);
    }
  }

  handleRemoveImage() {
    document.getElementsByClassName("img-input")[0].value = null;
  }

  showPreloadImage() {
    const { errorMessage, classes, imagePath } = this.props;
    const { file, imagePreviewUrl } = this.state;

    let comp = null;

    if (errorMessage) {
      comp = <Icon style={{ fontSize: 200 }}>error_outline</Icon>;
    } else if (file) {
      comp = (
        <img className={classes.avatarThumb} src={imagePreviewUrl} alt="..." />
      );
    } else if (imagePath) {
      comp = (
        <img
          className={classes.avatarThumb}
          alt="avatar"
          src={
            ConstantList.API_ENPOINT +
            "/public/hr/file/getImage/" +
            this.getImageNameAndType(imagePath)
          }
        />
      );
    } else {
      comp = <Icon style={{ fontSize: 320 }}>account_circle</Icon>;
    }
    return comp;
  }

  render() {
    const { errorMessage, title, classes } = this.props;
    const { name } = this.props.field;

    const avatarStyle = classnames(
      classes.bigAvatar,
      this.state.file ? [classes.whiteBack] : [classes.primaryBack],
      { [classes.errorBack]: errorMessage }
    );

    return (
      <div className={classes.container}>
        <input
          className={`${classes.hidden} img-input`}
          id={name}
          name={name}
          type="file"
          onChange={this.handleImageChange}
          ref={this.fileUpload}
        />
        <Typography className={classes.title} variant="h5">
          {title}
        </Typography>
        <Avatar
          color="inherit"
          className={avatarStyle}
          onClick={this.showFileUpload}
        >
          {this.showPreloadImage()}
        </Avatar>
        {!this.props.disableButton && (
          <>
            <Button
              startIcon={<CloudUploadIcon />}
              className="mr-0 btn btn-primary d-inline-flex mr-12"
              variant="contained"
              onClick={this.showFileUpload}
            >
              Đổi ảnh
            </Button>

            {/* <Button
              startIcon={<CloudUploadIcon />}
              className="mr-0 btn btn-primary d-inline-flex mr-12"
              variant="contained"
              onClick={this.showFileUpload}
            >
              Lưu
            </Button>

            <Button
              startIcon={<BlockIcon />}
              className="mr-0 btn btn-secondary d-inline-flex"
              variant="contained"
              onClick={this.handleRemoveImage}
            >
              Hủy
            </Button> */}
          </>
        )}

        {errorMessage ? (
          <Typography variant="caption" color="error">
            {errorMessage}
          </Typography>
        ) : null}
      </div>
    );
  }
}

export default withStyles(customImageInputStyle)(ImageInput);
