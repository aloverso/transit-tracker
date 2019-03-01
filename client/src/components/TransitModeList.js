import React, { Component } from 'react'
import TransitMode from "./TransitMode";
import { Query } from "react-apollo";
import gql from 'graphql-tag'

const GET_MODES = gql`
    query {
        modes {
            name
            counter
        }
    }
`

class TransitModeList extends Component {

  render() {
    return (
      <Query query={GET_MODES}>
        {({ loading, err, data }) => {
          if (loading) return <div>loading</div>
          if (err) return <div>error</div>

          return data.modes.map(mode => <TransitMode key={mode.name} mode={mode} />)
        }}
      </Query>
    )
  }
}

export default TransitModeList