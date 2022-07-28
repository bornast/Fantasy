import { RecordName } from "./recordName";

export class Match {
    id: number;
    homeTeam: MatchTeam;
    awayTeam: MatchTeam;
    goals: MatchGoal[];
    cards: MatchCard[];
    league: RecordName;
    matchDate: Date;
    referee: RecordName;
    stadium: RecordName;
    spectatorCount: number;
    name: string;
    result: string;
}

export class MatchTeam {
    team: RecordName;
    formation: RecordName;
    lineupPlayers: MatchPlayer[];
    substitutePlayers: MatchPlayer[];
    substitutions: MatchSubstitution[];
    coach: RecordName;
}

export class MatchSubstitution {
    lineupPlayer: MatchPlayer;
    substitutePlayer: MatchPlayer;
    minute: number;
}

export class MatchPlayer {
    id: number;
    name: string;
    rate: number;
}

export class MatchGoal {
    player: RecordName;
    minute: number;
}

export class MatchCard {
    player: RecordName;
    card: RecordName;
    minute: number;
}
