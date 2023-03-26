import React from 'react'

export const MatchDetailCard = ({ match }) => {
    if (!match)
        return null;
    else
        return (

            <div className='MatchDetailCard'>
                <h3>Latest details</h3>
                <h4>{match.team1} vs {match.team2}</h4>
            </div>
        )
}
