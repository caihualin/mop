/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.streamnative.pulsar.handlers.mqtt.common.adapter;

import static com.google.common.base.Preconditions.checkArgument;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.mqtt.MqttMessage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CombineAdapterHandler extends ChannelInboundHandlerAdapter {

    public static final String NAME = "combine-adapter-handler";

    MqttAdapterMessage adapterMessage;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) {
        if (message instanceof MqttAdapterMessage) {
            adapterMessage = (MqttAdapterMessage) message;
        } else {
            checkArgument(adapterMessage != null);
            adapterMessage.setMqttMessage((MqttMessage) message);
            ctx.fireChannelRead(adapterMessage);
        }
    }
}
