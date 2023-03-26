import { React, useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

export const TeamPage = () => {

    const [team, setTeam] = useState({ listOfMatchs: [] });

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch("http://localhost:8080/team/v1/Chennai%20Super%20Kings");
                const data = await response.json();
                console.log(data);
                setTeam(data);
            };
            fetchMatches();
        }, []

    );


    return (
        <div className='teamPage'>
            <h1>{team.teamName}</h1>
            <MatchDetailCard match={team.listOfMatchs[0]} />
            {team.listOfMatchs.slice(1)
                .map(match => <MatchSmallCard match={match} />)}
        </div >
    );
}
