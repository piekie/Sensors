package piekie.sensors.util;

import android.support.annotation.Nullable;

/**
 * Created by piekie (Artem Vasylenko)
 * on 10/21/16
 */

public class IntersectionCheceker {

    public final LineSegment segment1, segment2;
    private Point hasIntersection;

    public IntersectionCheceker(LineSegment segment1, LineSegment segment2) {
        this.segment1 = segment1;
        this.segment2 = segment2;
    }

    @Nullable
    public Point hasIntersection() {
        if (hasIntersection != null)
            return hasIntersection;

        if (segment1.isVertical) {
            if ((segment2.first.x - segment1.first.x) * (segment2.second.x - segment1.first.x) > 0)
                hasIntersection = null;
            else {
                double fx_at_segment1firstx = segment1.slope * segment1.first.x + segment1.intercept;
                double smaller, larger;
                if (segment1.first.x < segment1.second.x) {
                    smaller = segment1.first.x;
                    larger = segment1.second.x;
                } else {
                    larger = segment1.first.x;
                    smaller = segment1.second.x;
                }
                if (smaller <= fx_at_segment1firstx && fx_at_segment1firstx <= larger) {

                    hasIntersection = new Point(0, 0);

                } else {
                    hasIntersection = null;
                }
            }
        } else if (segment2.isVertical) {
            hasIntersection = new IntersectionCheceker(segment2, segment1).hasIntersection();
        } else { //both segment1 and segment2 are not vertical
            if (segment1.slope == segment2.slope)
                hasIntersection = null;
            else {
                double x1 = segment1.first.x;
                double y1 = segment1.first.y;
                double x2 = segment1.second.x;
                double y2 = segment1.second.y;
                double x3 = segment2.first.x;
                double y3 = segment2.first.y;
                double x4 = segment2.second.x;
                double y4 = segment2.second.y;
                double x = ((x4 * y3 - y4 * x3) / (x4 - x3) - (x2 * y1 - y2 * x1) / (x2 - x1))
                        / ((y2 - y1) / (x2 - x1) - (y4 - y3) / (x4 - x3));

                double smaller1, larger1;
                double smaller2, larger2;

                if (x1 < x2) {
                    smaller1 = x1;
                    larger1 = x2;
                } else {
                    smaller1 = x2;
                    larger1 = x1;
                }
                if (x3 < x4) {
                    smaller2 = x3;
                    larger2 = x4;
                } else {
                    smaller2 = x4;
                    larger2 = x3;
                }
                if (smaller1 <= x && x <= larger1 && smaller2 <= x && x <= larger2) {

                    hasIntersection = new Point(x, 0);
                } else
                    hasIntersection = null;
            }
        }
        return hasIntersection;
    }
}
