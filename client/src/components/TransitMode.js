import React, { Component } from 'react'

class TransitMode extends Component {
  render() {
    return (
      <div>
        <div>
          {this.props.mode.name} ({this.props.mode.counter})
        </div>
      </div>
    )
  }
}

export default TransitMode