import { EntityMedia, Media } from "./media";
import { RecordName } from "./recordName";
import { Season } from "./season";

export interface League {
    id: number;
    name: string;
    season: RecordName;
    teams: RecordName[];
}
