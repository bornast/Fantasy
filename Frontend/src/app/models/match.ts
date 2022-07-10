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
    lineupPlayers: RecordName[];
    substitutePlayers: RecordName[];
    substitutions: MatchSubstitution[];
}

export class MatchSubstitution {
    lineupPlayer: RecordName;
    substitutePlayer: RecordName;
    minute: number;
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
