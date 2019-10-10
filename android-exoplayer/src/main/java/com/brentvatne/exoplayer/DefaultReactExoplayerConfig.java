package com.brentvatne.exoplayer;

import android.content.Context;

import com.brentvatne.exoplayer.bitrate.BitrateAdaptionPreset;
import com.brentvatne.exoplayer.bitrate.DefaultBitrateAdaptionPreset;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultLoadErrorHandlingPolicy;
import com.google.android.exoplayer2.upstream.LoadErrorHandlingPolicy;

public class DefaultReactExoplayerConfig implements ReactExoplayerConfig {

    private BitrateAdaptionPreset bitrateAdaptionPreset;

    private final DefaultBandwidthMeter bandwidthMeter;

    public DefaultReactExoplayerConfig(Context context) {
        this.bitrateAdaptionPreset = new DefaultBitrateAdaptionPreset();
        this.bandwidthMeter = new DefaultBandwidthMeter.Builder(context)
                .setInitialBitrateEstimate(this.bitrateAdaptionPreset.maxInitialBitrate())
                .setSlidingWindowMaxWeight(this.bitrateAdaptionPreset.bandwidthMeterMaxWeight())
                .build();
    }

    @Override
    public LoadErrorHandlingPolicy buildLoadErrorHandlingPolicy(int minLoadRetryCount) {
        return new DefaultLoadErrorHandlingPolicy(minLoadRetryCount);
    }

    @Override
    public DefaultBandwidthMeter getBandwidthMeter() {
        return bandwidthMeter;
    }

    @Override
    public BitrateAdaptionPreset getBitrateAdaptionPreset() { return bitrateAdaptionPreset; }
}
