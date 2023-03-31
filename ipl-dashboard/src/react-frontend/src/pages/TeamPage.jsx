import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

export const TeamPage = () => {

    const [team, setTeam] = useState({ listOfMatchs: [] });

    const { teamName } = useParams();

    useEffect(
        () => {
            const fetchMatches = async () => {
                const response = await fetch(`http://localhost:8080/team/v1/${teamName}`);
                const data = await response.json();
                console.log(data);
                setTeam(data);
            };
            fetchMatches();
        }, [teamName]

    );

    if (!team || !team.teamName) {
        return <h1>Team not found</h1>
    }

    return (
        <div className='teamPage'>
            <h1>{team.teamName}</h1>
            <MatchDetailCard teamName={team.teamName} match={team.listOfMatchs[0]} />
            {team.listOfMatchs.slice(1)
                .map(match => <MatchSmallCard teamName={team.teamName} match={match} />)}
        </div >
    );
}
